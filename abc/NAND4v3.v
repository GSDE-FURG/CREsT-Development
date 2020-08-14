// Benchmark "C17.blif" written by ABC on Sat Apr 29 20:12:48 2017

module NAND4v2 ( 
    A, B, C, D, OUT );
  input  A, B, C, D;
  output OUT ;
  wire n1, n2, n3, n4;
  NAND2X1  g0(.A(A), .B(B), .Y(n1));
  NAND2X1  g1(.A(C), .B(D), .Y(n2));
  INVX1    g2(.A(n1), .Y(n3));
  INVX1    g3(.A(n2), .Y(n4));
  NAND2X1  g4(.A(n3), .B(n4), .Y(OUT)); 
endmodule
