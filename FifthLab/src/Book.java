import java.util.Random;

public class Book {
    public String Name;
    public String Author;
    public int Pages;
    public int o;
    private int[] A;

    public Book()
    {
        Name = "";
        Author = "";
        Pages = 0;
        o = 1;
        A = new int[10000000];
        for (int i : A) {
            A[i] = 0;
        }
    }

    public Book(String Name, String Author, int Pages, int o)
    {
        this.Name = Name;
        this.Author = Author;
        this.Pages = Pages;
        if (o != 0)
        {
            A = new int[10000000];
            Random rand = new Random();
            for (int i = 0; i < A.length; ++i)
            {
                A[i] = rand.nextInt();
            }
        }
    }
}
