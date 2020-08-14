// Benchmark "C17.blif" written by ABC on Sat Apr 29 20:12:48 2017

module NAND4v2 ( 
    A, B, C, D, OUT );
  input  A, B, C, D;
  output OUT ;
  wire n1, n2;
  NAND3X1  g0(.A(A), .B(B), .C(C), .Y(n1));
  INVX1    g1(.A(n1), .Y(n2));
  NAND2X1  g2(.A(n2), .B(D), .Y(OUT)); 
endmodule


