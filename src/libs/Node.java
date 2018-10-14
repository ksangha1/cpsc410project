package libs;



import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public abstract class Node {
    static protected PrintWriter writer;
    protected Tokenizer tokenizer = Tokenizer.getTokenizer();
    abstract public void parse();
    abstract public void evaluate();
}