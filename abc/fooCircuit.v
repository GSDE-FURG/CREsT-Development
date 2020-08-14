module fooCircuit (
    C0, P0, P1, G0, G1, Out );
  input  C0, P0, P1, G0, G1;
  output Out;
  wire w1, w2;
  
  AND3X1    G000(.A(C0), .B(P0), .C(P1), .Y(w1));
  AND2X1    G001(.A(P1), .B(G0), .Y(w2));
  AND3X1    G002(.A(w1), .B(w2), .C(G1), .Y(Out));

endmodule


