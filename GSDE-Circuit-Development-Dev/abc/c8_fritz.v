module C8Fritz (
    G1gat, G2gat, G3gat, G4gat, G5gat, G6gat, G7gat );
  input  G1gat, G2gat, G3gat, G4gat;
  output G5gat, G6gat, G7gat ;
  wire w1, w2, w3, w4, w5;

  
  NAND2X1   U1(.A(G1gat), .B(G2gat), .Y(w1));
  NOR2X1    U2(.A(G2gat), .B(G3gat), .Y(w2));
  AND2X1    U3(.A(G3gat), .B(G4gat), .Y(w3));

  AND2X1    U4(.A(w1), .B(w2), .Y(w4));
  OR2X1     U5(.A(w2), .B(w3), .Y(w5));

  OR2X1     U6(.A(G1gat), .B(w4), .Y(G5gat));
  AND2X1    U7(.A(w4), .B(w5), .Y(G6gat));
  NOR2X1    U8(.A(w5), .B(G4gat), .Y(G7gat));

endmodule


