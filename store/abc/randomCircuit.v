module RandomCircuit (
    S1, S2, S3, S4, O1, O2, w1, w2, w3 );
  input  S1, S2, S3, S4;
  output O1, O2;
  wire w1, w2, w3;

  AND2X1    G001(.A(S1), .B(S2), .Y(w1));
  INVX1     G002(.A(S3), .Y(w2));
  NAND2X1   G003(.A(S2), .B(S4), .Y(w3));
  OR2X1     G004(.A(w1), .B(w2), .Y(O1));
  INVX1     G005(.A(w3), .Y(O2));

endmodule


