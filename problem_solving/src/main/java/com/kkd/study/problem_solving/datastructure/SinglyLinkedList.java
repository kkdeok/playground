package com.kkd.study.problem_solving.datastructure;

import java.io.*;

public class SinglyLinkedList {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static class Node {
        int key;
        Node next;
    }

    private static void ins(Node head, int key) {
        Node p = head.next;
        Node prev = head;

        while (p != null) {
            if (p.key > key) {
                break;
            }
            prev = p;
            p = p.next;
        }
        Node newNode = new Node();
        newNode.key = key;

        prev.next = newNode;
        newNode.next = p;
    }

    private static void del(Node head, int key) {
        Node p = head.next;
        Node prev = head;

        while (p != null) {
            if (p.key == key) {
                break;
            }
            prev = p;
            p = p.next;
        }
        prev.next = p.next;
    }

    private static void print(Node head) throws IOException {
        Node p = head.next;
        while (p != null) {
            bw.write(p.key + " ");
            p = p.next;
        }
        bw.write("\n");
    }

    public static void main(String[] args) throws IOException {
        Node head = new Node();
        head.next = null;
        // insert 5 values
        for (int i = 0; i < 5; i++) {
            int key = Integer.parseInt(br.readLine());
            ins(head, key);
            print(head);
        }
        // remove 3 values
        for (int i = 0; i < 3; i++) {
            int key = Integer.parseInt(br.readLine());
            del(head, key);
            print(head);
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
