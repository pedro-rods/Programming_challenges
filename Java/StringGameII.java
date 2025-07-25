/*
 * LeetCode Problem 3307: Find the K-th Character in String Game II
 * link: https://leetcode.com/problems/find-the-k-th-character-in-string-game-ii/?envType=daily-question
 */

public class StringGameII {
    public static void main(String[] args) {
        long k = 33354182522397L;
        int[] operations = {
            0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1,
            1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0,
            1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1,
            0, 1, 1, 1, 1, 1, 1, 0, 0, 0
        };
        char result = kthCharacter(k, operations);

        System.out.println("Resultado: " + result);
    }

    public static char kthCharacter(long k, int[] operations) {
        // A pilha vai nos ajudar a simular o backtrack sem rastrear tamanho real

        // Cria um array com os tamanhos virtuais após cada operação
        long[] lengths = new long[operations.length + 1];
        lengths[0] = 1;
        for (int i = 0; i < operations.length; i++) {
            if (lengths[i] > Long.MAX_VALUE / 2) {
                lengths[i + 1] = Long.MAX_VALUE; // Limitar no limite de long
            } else {
                lengths[i + 1] = lengths[i] * 2;
            }
        }

        long transform = 0;

        // Backtrack reverso
        for (int i = operations.length - 1; i >= 0; i--) {
            long half = lengths[i];
            if (operations[i] == 0) {
                if (k > half) {
                    k -= half;
                }
            } else {
                if (k > half) {
                    k -= half;
                    transform++;
                }
            }
        }

        return (char) ('a' + (transform % 26));
    }
}
