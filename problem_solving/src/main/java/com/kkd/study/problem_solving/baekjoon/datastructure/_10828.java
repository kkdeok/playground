package com.kkd.study.problem_solving.baekjoon.datastructure;

import java.io.*;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * https://www.acmicpc.net/problem/10828
 */
public class _10828 {
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        start();
        bw.flush();
        bw.close();
        br.close();
    }

    private static void start() throws IOException {
//        Stack<Integer> stack = new Stack<>();
        CustomizedStack<Integer> stack = new CustomizedStack();

        int n = Integer.parseInt(br.readLine());
        while (n > 0) {
            String[] input = br.readLine().split(" ");
            String cmd = input[0];
            if ("push".equals(cmd)) {
                int v = Integer.parseInt(input[1]);
                stack.push(v);
            } else if ("pop".equals(cmd)) {
                if (stack.empty()) {
                    bw.write("-1\n");
                } else {
                    bw.write(stack.pop() + "\n");
                }
            } else if ("size".equals(cmd)) {
               bw.write(stack.size() + "\n");
            } else if ("empty".equals(cmd)) {
                bw.write((stack.empty() ? "1" : "0") + "\n");
            } else if ("top".equals(cmd)) {
                if (stack.empty()) {
                    bw.write("-1\n");
                } else {
                    bw.write(stack.peek() + "\n");
                }
            }
            n--;
        }
    }

    static class CustomizedStack<T> {
        class Node {
            T key;
            Node next;
        }
        private Node head;

        CustomizedStack() {
            head = new Node();
            head.next = null;
        }

        synchronized void push(T key) {
            Node p = head.next;
            Node prev = head;

            while (p != null) {
                prev = p;
                p = p.next;
            }
            Node newNode = new Node();
            newNode.key = key;

            prev.next = newNode;
            newNode.next = p;
        }

        synchronized T pop() {
            Node p = head.next;
            Node prev = head;

            if (head.next == null) {
                throw new EmptyStackException();
            }

            while (p.next != null) {
                prev = p;
                p = p.next;
            }

            prev.next = null;
            return p.key;
        }

        synchronized boolean empty() {
           return size() == 0;
        }

        synchronized int size() {
            Node p = head.next;
            int cnt = 0;
            while (p != null) {
                cnt++;
                p = p.next;
            }
            return cnt;
        }

        synchronized T peek() {
            Node p = head.next;
            if (head.next == null) {
                throw new EmptyStackException();
            }
            while (p.next != null) {
                p = p.next;
            }
            return p.key;
        }
    }
}