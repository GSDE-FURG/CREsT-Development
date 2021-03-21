// Benchmark "xor6.blif" written by ABC on Thu Mar 18 05:31:23 2021

module \xor6.blif  ( 
    A, B, C, D, E, F,
    Y  );
  input  A, B, C, D, E, F;
  output Y;
  wire new_n8_, new_n9_, new_n10_, new_n11_, new_n12_, new_n13_, new_n14_,
    new_n15_, new_n16_, new_n17_, new_n18_;
  INV    g00(.A(E), .Y(new_n8_));
  XOR2   g01(.A(F), .B(new_n8_), .Y(new_n9_));
  INV    g02(.A(C), .Y(new_n10_));
  XOR2   g03(.A(D), .B(new_n10_), .Y(new_n11_));
  INV    g04(.A(A), .Y(new_n12_));
  XOR2   g05(.A(B), .B(new_n12_), .Y(new_n13_));
  XOR2   g06(.A(D), .B(C), .Y(new_n14_));
  XOR2   g07(.A(B), .B(A), .Y(new_n15_));
  AOI22  g08(.A0(new_n15_), .A1(new_n14_), .B0(new_n13_), .B1(new_n11_), .Y(new_n16_));
  XOR2   g09(.A(F), .B(E), .Y(new_n17_));
  AOI22  g10(.A0(new_n15_), .A1(new_n11_), .B0(new_n14_), .B1(new_n13_), .Y(new_n18_));
  OAI22  g11(.A0(new_n18_), .A1(new_n17_), .B0(new_n16_), .B1(new_n9_), .Y(Y));
endmodule


