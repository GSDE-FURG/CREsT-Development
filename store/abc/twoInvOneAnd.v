module twoInvOneAnd (
    S1, S2, O1, w1, w2 );
  input  S1, S2;
  output O1;
  wire w1, w2;
  
  INVX1     G001(.A(S1), .Y(w1));
  INVX1     G002(.A(S2), .Y(w2));
  AND2X1    G003(.A(w1), .B(w2), .Y(O1));

endmodule


