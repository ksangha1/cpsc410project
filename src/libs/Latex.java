package libs;

public class Latex {

    private static StringBuilder sb;

    public Latex()
    {
      sb = new StringBuilder();
      sb.append("\\documentclass{article}\n" +
              "\\usepackage{tikz}\n" +
              "\\usetikzlibrary{positioning}\n" +
              "\\usetikzlibrary{automata,positioning}\n" +
              "\\begin{document}\n" +
              "\\begin{tikzpicture}[shorten >=1pt,node distance=2cm,on grid,auto]\n");
    }


    public StringBuilder getSb()
    {
        return sb;
    }

    public void addSB(StringBuilder input)
    {
        sb.append(input);
    }

    public void endSB()
    {
        sb.append( "\\end{tikzpicture}\n" +
                    "\\end{document}");
    }
}
