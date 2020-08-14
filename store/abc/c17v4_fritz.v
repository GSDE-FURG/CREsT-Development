module C17V4 (
    G1gat, G2gat, G3gat, G4gat, G5gat, G6gat, G7gat );
  input  G1gat, G2gat, G3gat, G4gat, G5gat;
  output G6gat, G7gat ;
  wire w1, w2, w3, w4, w5;

  AND2X1    U1(.A(G5gat), .B(G2gat), .Y(w1));
  AND2X1    U3(.A(G5gat), .B(G1gat), .Y(w2));
  INVX1     U0(.A(G4gat), .Y(w3));
  NOR2X1    U2(.A(w1), .B(w3), .Y(w4));
  NOR2X1    U5(.A(G4gat), .B(G3gat), .Y(w5));  
  OR2X1     U4(.A(w2), .B(w4), .Y(G6gat));
  NOR2X1    U6(.A(w5), .B(w1), .Y(G7gat));

endmodule


