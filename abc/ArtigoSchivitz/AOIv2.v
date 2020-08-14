// Benchmark "C17.blif" written by ABC on Sat Apr 29 20:12:48 2017

module C17.blif ( 
    A, B, C, OUT );
  input  A, B, C;
  output OUT ;
  wire n1, n2, n3;
  INVX1    g0(.A(A), .Y(n1));
  NAND2X1  g1(.A(B), .B(C), .Y(n2)); 
  NAND2X1  g2(.A(n1), .B(n2), .Y(n3));
  INVX1    g3(.A(n3), .Y(OUT));
endmodule


