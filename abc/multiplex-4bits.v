module Multiplex_4bits (
    G1gat, G2gat, G3gat, G4gat, G5gat, G6gat, G7gat );
  input  G1gat, G2gat, G3gat, G4gat, G5gat, G6gat;
  output G7gat ;
  wire w1, w2, w3, w4, w5, w6;

  INVX1     U1(.A(G1gat), .Y(w1));
  INVX1     U2(.A(G2gat), .Y(w2));
  AND3X1    U3(.A(G3gat), .B(w2), .C(w1), .Y(w3));
  AND3X1    U4(.A(G4gat), .B(w2), .C(G1gat), .Y(w4));
  AND3X1    U5(.A(G5gat), .B(G2gat), .C(w1), .Y(w5));
  AND3X1    U6(.A(G6gat), .B(G2gat), .C(G1gat), .Y(w6));
  OR4X1     U7(.A(w3), .B(w4), .C(w5), .D(w6), .Y(G7gat));

endmodule


