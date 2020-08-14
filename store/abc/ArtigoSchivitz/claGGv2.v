// Benchmark "C17.blif" written by ABC on Sat Apr 29 20:12:48 2017

module claGGv2 ( 
    A, B, C, D, E, F, G, OUT );
  input  A, B, C, D, E, F, G;
  output OUT ;
  wire n1, n2, n3, n4, n5. n6;
  NAND4X1  g0(.A(A), .B(F), .C(G), .D(E), .Y(n1));
  NAND2X1  g1(.A(C), .B(G), .Y(n2));
  NAND3X1  g2(.A(B), .B(F), .C(G), .Y(n3));
  INVX1    g3(.A(n1), .Y(n4));
  INVX1    g4(.A(n2), .Y(n5));
  INVX1    g5(.A(n3), .Y(n6));
  NOR4X1   g6(.A(D), .B(n4), .C(n5), .D(n6), .Y(OUT));
endmodule


