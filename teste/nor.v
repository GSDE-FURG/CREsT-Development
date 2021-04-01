module C17V3 (
    G1gat, G2gat, G3gat, G4gat, G5gat, G6gat, G7gat );
  input  G1gat, G2gat, G3gat, G4gat, G5gat;
  output G6gat, G7gat ;
  wire w1, w2, w3, w4;
  
  NOR2X1   U0(.A(G5gat), .B(G2gat), .Y(w1));
  

endmodule


