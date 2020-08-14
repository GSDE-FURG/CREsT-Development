// Benchmark "C17.blif" written by ABC on Sat Apr 29 20:12:48 2017

module C17.blif ( 
    A, B, C, OUT );
  input  A, B, C;
  output OUT ;
  wire n1, n2;
  NAND2X1  g0(.A(B), .B(C), .Y(n1));
  INVX1    g1(.A(n1), .Y(n2)); 
  NOR2X1   g2(.A(A), .B(n2), .Y(OUT));
endmodule


