// Benchmark "xor4.blif" written by ABC on Thu Mar 18 04:31:58 2021

module \xor4.blif  ( 
    A, B, C, D,
    Y  );
  input  A, B, C, D;
  output Y;
  wire new_n6_, new_n7_, new_n8_, new_n9_, new_n10_, new_n11_, new_n12_,
    new_n13_, new_n14_, new_n15_, new_n16_, new_n17_, new_n18_, new_n19_,
    new_n20_, new_n21_, new_n22_, new_n23_;
  INV   g00(.A(C), .Y(new_n6_));
  NAND2 g01(.A(D), .B(new_n6_), .Y(new_n7_));
  INV   g02(.A(D), .Y(new_n8_));
  NAND2 g03(.A(new_n8_), .B(C), .Y(new_n9_));
  NAND2 g04(.A(new_n9_), .B(new_n7_), .Y(new_n10_));
  NAND2 g05(.A(B), .B(A), .Y(new_n11_));
  INV   g06(.A(A), .Y(new_n12_));
  INV   g07(.A(B), .Y(new_n13_));
  NAND2 g08(.A(new_n13_), .B(new_n12_), .Y(new_n14_));
  NAND2 g09(.A(new_n14_), .B(new_n11_), .Y(new_n15_));
  NAND2 g10(.A(new_n15_), .B(new_n10_), .Y(new_n16_));
  NAND2 g11(.A(D), .B(C), .Y(new_n17_));
  NAND2 g12(.A(new_n8_), .B(new_n6_), .Y(new_n18_));
  NAND2 g13(.A(new_n18_), .B(new_n17_), .Y(new_n19_));
  NAND2 g14(.A(B), .B(new_n12_), .Y(new_n20_));
  NAND2 g15(.A(new_n13_), .B(A), .Y(new_n21_));
  NAND2 g16(.A(new_n21_), .B(new_n20_), .Y(new_n22_));
  NAND2 g17(.A(new_n22_), .B(new_n19_), .Y(new_n23_));
  NAND2 g18(.A(new_n23_), .B(new_n16_), .Y(Y));
endmodule


