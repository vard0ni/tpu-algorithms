public class Deque {
    Node front;
    Node back;
    int c;
    public Deque()
    {
        front = null;
        back = null;
    }
    public void push_back(Object data)
    {
        var newNode = new Node(data);
        if (front == null)
        {
            front = newNode;
        }
        else
        {
            back.next = newNode;
            newNode.previous = back;
        }
        back = newNode;
        c++;
    }
    public void push_front(Object data)
    {
        var newNode = new Node(data);
        var temp = front;
        newNode.next = temp;
        front = newNode;
        if (c == 0)
        {
            back = front;
        }
        else 
        {
            temp.previous = newNode;
        }
        c++;
    }
    public Object pop_back()
    {
        if (c == 0)
        {
            return 0;
        }
        var r = back.data;
        if (c == 1)
        {
            front = back = null;
        }
        else
        {
            back = back.previous;
            back.next = null;
        }
        c--;
        return r;
    }
    public Object pop_front()
    {
        if (c == 0)
        {
            return null;
        }
        var r = front.data;
        if (c == 1)
        {
            front = back = null;
        }
        else
        {
            front = front.next;
            front.previous = null;
        }
        c--;
        return r;
    }
}
