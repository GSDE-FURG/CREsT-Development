// Benchmark "C17.blif" written by ABC on Sat Apr 29 20:12:48 2017

module NAND4v1 ( 
    A, B, C, D, OUT );
  input  A, B, C, D;
  output OUT ;
  NAND4X1 g0(.A(A), .B(B), .C(C), .D(D), .Y(OUT)); 
endmodule


