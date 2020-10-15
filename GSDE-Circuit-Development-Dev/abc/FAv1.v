module FullAdderV1 (
    G1gat, G2gat, G3gat, G4gat, G5gat );
  input  G1gat, G2gat, G3gat;
  output G4gat, G5gat ;
  wire w1, w2, w3, w4;

  XOR2X1    G1(.A(G1gat), .B(G2gat), .Y(w1));  
  AND2X1    G2(.A(G1gat), .B(G2gat), .Y(w2));
  AND2X1    G3(.A(G1gat), .B(G3gat), .Y(w3));
  AND2X1    G4(.A(G3gat), .B(G2gat), .Y(w4));
  XOR2X1    G5(.A(w1), .B(G3gat), .Y(G4gat));
  OR3X1     G6(.A(w2), .B(w3), .C(w4), .Y(G5gat));

endmodule


