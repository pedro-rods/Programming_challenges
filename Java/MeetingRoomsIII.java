/*
 * LeetCode Problem 2402: Meeting Rooms III
 * link: https://leetcode.com/problems/meeting-rooms-iii/?envType=daily-question
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRoomsIII {

    public static int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, Comparator.comparing(e -> e[0]));

        PriorityQueue<Integer> salasLivre = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            salasLivre.offer(i);
        }

        PriorityQueue<int[]> salasOcupadas = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0])
                return a[1] - b[1];
            return a[0] - b[0];
        });

        int[] contagemDasSalas = new int[n];

        for (int[] meeting : meetings) {
            int start = meeting[0], end = meeting[1];
            int duration = end - start;

            while (!salasOcupadas.isEmpty() && salasOcupadas.peek()[0] <= start) {
                int[] liberado = salasOcupadas.poll();
                salasLivre.offer(liberado[1]);
            }

            if (!salasLivre.isEmpty()) { // possui sala livre
                int sala = salasLivre.poll();
                contagemDasSalas[sala]++;
                salasOcupadas.offer(new int[] { end, sala });
            } else { // n possui sala livre, atrasar
                int[] proximaLivre = salasOcupadas.poll();
                int newStart = proximaLivre[0];
                int newEnd = newStart + duration;
                int sala = proximaLivre[1];
                contagemDasSalas[sala]++;
                salasOcupadas.offer(new int[] { newEnd, sala });
            }
        }

        int maisUsado = 0, salaResultado = 0;
        for (int i = 0; i < n; i++) {
            if (contagemDasSalas[i] > maisUsado) {
                maisUsado = contagemDasSalas[i];
                salaResultado = i;
            }
        }

        return salaResultado;
    }

    public static void main(String[] args) {
        int[][] meetings = { { 0, 10 }, { 1, 5 }, { 2, 7 }, { 3, 4 } };
        int result = mostBooked(2, meetings);
        System.out.println(result);
    }
}
