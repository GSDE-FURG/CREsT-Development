module C17V3_Classic (
    I1, I2, I3, I4, I5, O1, O2 );
  input  I1, I2, I3, I4, I5;
  output O1, O2;
  wire w1, w2, w3, w4;

  
  NAND2X1   G1(.A(I1), .B(I3), .Y(w1));
  NAND2X1   G2(.A(I3), .B(I4), .Y(w2));
  
  NAND2X1   G3(.A(I2), .B(w2), .Y(w3));
  NAND2X1   G4(.A(w2), .B(I5), .Y(w4));

  NAND2X1   G5(.A(w1), .B(w3), .Y(O1));
  NAND2X1   G6(.A(w3), .B(w4), .Y(O2));

endmodule


