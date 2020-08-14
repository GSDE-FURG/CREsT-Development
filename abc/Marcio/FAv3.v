module FullAdderV3Fritz (
    G1gat, G2gat, G3gat, G4gat, G5gat );
  input  G1gat, G2gat, G3gat;
  output G4gat, G5gat ;
  wire w1, w2, w3, w4, w5, w6, w7, w8, w9, w10;

  INVX1     G000(.A(G1gat), .Y(w1));
  INVX1     G001(.A(G2gat), .Y(w2));
  INVX1     G002(.A(G3gat), .Y(w3));  

  AND3X1    G003(.A(G3gat), .B(w2), .C(w1), .Y(w4));
  AND3X1    G004(.A(w3), .B(G2gat), .C(w1), .Y(w5));
  AND3X1    G005(.A(w3), .B(w2), .C(G1gat), .Y(w6));
  AND3X1    G006(.A(G3gat), .B(G2gat), .C(G1gat), .Y(w7));
  AND2X1    G007(.A(G3gat), .B(G2gat), .Y(w8));
  AND2X1    G008(.A(G3gat), .B(G1gat), .Y(w9));
  AND2X1    G009(.A(G2gat), .B(G1gat), .Y(w10));

  OR4X1     G010(.A(w4), .B(w5), .C(w6), .D(w7), .Y(G4gat));
  OR3X1     G011(.A(w8), .B(w9), .C(w10), .Y(G5gat));

endmodule


