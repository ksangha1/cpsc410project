package libs;

public class Latex {

    private static StringBuilder sb;

    public Latex()
    {
      sb = new StringBuilder();
      sb.append("\\documentclass{article}\n" +
              "\n" +
              "\\usepackage{tikz}\n" +
              "\\usetikzlibrary{automata,positioning}\n" +
              "\\begin{document}\n" +
              "\\begin{tikzpicture}[shorten >=1pt,node distance=2cm,on grid,auto]\n " +
               "USERINPUT\n" +
              "\\end{tikzpicture}\n" +
              "\\end{document}");
    }


    public StringBuilder getSb()
    {
        return sb;
    }

    public void addSB(String input)
    {
        sb.append(input);
    }
}
