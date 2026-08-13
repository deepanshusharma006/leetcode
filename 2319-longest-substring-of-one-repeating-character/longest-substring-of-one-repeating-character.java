class Solution {

    class Node {
        char leftChar;
        char rightChar;
        int length;
        int prefix;
        int suffix;
        int answer;

        Node(char leftChar, char rightChar, int length,
             int prefix, int suffix, int answer) {

            this.leftChar = leftChar;
            this.rightChar = rightChar;
            this.length = length;
            this.prefix = prefix;
            this.suffix = suffix;
            this.answer = answer;
        }
    }

    Node[] tree;
    String s;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {

        this.s = s;

        int n = s.length();

        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[queryIndices.length];

        for (int i = 0; i < queryIndices.length; i++) {

            int index = queryIndices[i];
            char ch = queryCharacters.charAt(i);

            update(1, 0, n - 1, index, ch);

            ans[i] = tree[1].answer;
        }

        return ans;
    }

    void build(int node, int start, int end) {

        if (start == end) {

            char ch = s.charAt(start);

            tree[node] = new Node(
                ch, ch, 1, 1, 1, 1
            );

            return;
        }

        int mid = (start + end) / 2;

        build(node * 2, start, mid);

        build(node * 2 + 1, mid + 1, end);

        tree[node] = merge(
            tree[node * 2],
            tree[node * 2 + 1]
        );
    }

    Node merge(Node left, Node right) {

        Node res = new Node(
            left.leftChar,
            right.rightChar,
            left.length + right.length,
            left.prefix,
            right.suffix,
            Math.max(left.answer, right.answer)
        );

        if (left.rightChar == right.leftChar) {

            res.answer = Math.max(
                res.answer,
                left.suffix + right.prefix
            );

            if (left.prefix == left.length) {
                res.prefix = left.length + right.prefix;
            }

            if (right.suffix == right.length) {
                res.suffix = right.length + left.suffix;
            }
        }

        return res;
    }

    void update(int node, int start, int end,
                int index, char ch) {

        if (start == end) {

            tree[node] = new Node(
                ch, ch, 1, 1, 1, 1
            );

            return;
        }

        int mid = (start + end) / 2;

        if (index <= mid) {

            update(node * 2, start, mid, index, ch);

        } else {

            update(node * 2 + 1, mid + 1, end, index, ch);
        }

        tree[node] = merge(
            tree[node * 2],
            tree[node * 2 + 1]
        );
    }
}