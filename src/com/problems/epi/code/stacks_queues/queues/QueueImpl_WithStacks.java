package com.problems.epi.code.stacks_queues.queues;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Key Insight: In a Queue, insertions happen at the back while deletions happen at the front.
 * In a stack, insertions and deleteions (the back and front in a queue) happen at the top of the stack.
 * Hence, use two stacks- one to enqueue (acting as the back of the queue) and the other to dequeue (acting as the front of the queue).
 * In this way, popping elements from one stack and pushing to another stack reverses the order of the elements.
 * Note that some dequeue operations may take O(n) but this complexity is amortized over time because it rarely happens. Hence, enqueque and dequeue take O(1) overall.
 * Time complexity for all operations: O(1)
 * Space Complexity: O(n)
 */
public class QueueImpl_WithStacks<T> {

    Deque<Integer> stackNewest; // holds newest queue elements
    Deque<Integer> stackOldest; // holds oldest queue elements
    int size = 0;
    public QueueImpl_WithStacks() {
        stackNewest = new ArrayDeque<>();
        stackOldest = new ArrayDeque<>();
    }
    public void enqueue(Integer val) {
        stackNewest.push(val);
        size++;
    }

    public Integer dequeue() {
        if(stackOldest.isEmpty()) moveStack();
        size--;
        return stackOldest.pop();
    }

    private void moveStack() {
        if(stackNewest.isEmpty()) throw new IllegalStateException();
        while(!stackNewest.isEmpty()) stackOldest.push(stackNewest.pop());
    }

    public int front() {
        if(stackOldest.isEmpty()) moveStack();
        return stackOldest.peek();
    }

    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }
}
