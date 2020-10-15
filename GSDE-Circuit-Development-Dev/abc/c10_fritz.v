module C10Fritz (
    G1gat, G2gat, G3gat, G4gat, G5gat, G6gat, G7gat, G8gat, G9gat );
  input  G1gat, G2gat, G3gat, G4gat, G5gat, G6gat, G7gat, G8gat;
  output G9gat ;
  wire w1, w2, w3, w4, w5, w6, w7, w8, w9;

  NAND2X1   U0(.A(G1gat), .B(G2gat), .Y(w1));
  INVX1     U2(.A(G5gat), .Y(w2));
  INVX1     U3(.A(G8gat), .Y(w3));

  NAND2X1   U4(.A(w1), .B(w2), .Y(w4));
  NAND2X1   U1(.A(G3gat), .B(G4gat), .Y(w5));
  AND2X1    U5(.A(G6gat), .B(G7gat), .Y(w6));
  AND2X1    U6(.A(G7gat), .B(w3), .Y(w7));

  AND2X1    U8(.A(w4), .B(w5), .Y(w8));
  NAND2X1   U7(.A(w6), .B(w7), .Y(w9));

  OR2X1     U9(.A(w8), .B(w9), .Y(G9gat));

endmodule


