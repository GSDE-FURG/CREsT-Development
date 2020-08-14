module invAndCircuit (
    S1 O1, w1);
  input  S1;
  output O1;
  wire w1;
  
  INVX1     G001(.A(S1), .Y(w1));
  AND2X1    G002(.A(w1), .B(S1), .Y(O1));

endmodule


