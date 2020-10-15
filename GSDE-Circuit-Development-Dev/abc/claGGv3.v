// Benchmark "C17.blif" written by ABC on Sat Apr 29 20:12:48 2017

module claGGv2 ( 
    A, B, C, D, E, F, G, OUT );
  input  A, B, C, D, E, F, G;
  output OUT ;
  wire n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16;
  NAND2X1  g0(.A(A), .B(F), .Y(n1));
  NAND2X1  g1(.A(G), .B(E), .Y(n2));
  NAND2X1  g2(.A(C), .B(G), .Y(n3));
  NAND2X1  g3(.A(B), .B(F), .Y(n4));
  INVX1    g4(.A(n1), .Y(n5));
  INVX1    g5(.A(n2), .Y(n6));
  INVX1    g6(.A(n4), .Y(n7));
  NAND2X1  g7(.A(n5), .B(n6), .Y(n8));
  NAND2X1  g8(.A(n7), .B(G), .Y(n9));
  INVX1    g9(.A(n8), .Y(n10));
  INVX1    g10(.A(n3), .Y(n11));
  INVX1    g11(.A(n9), .Y(n12));
  NOR2X1   g12(.A(D), .B(n10), .Y(n13));
  NOR2X1   g13(.A(n11), .B(n12), .Y(n14));
  INVX1    g14(.A(n13), .Y(n15));
  INVX1    g15(.A(n14), .Y(n16));
  NOR2X1   g16(.A(n15), .B(n16), .Y(OUT));
endmodule


