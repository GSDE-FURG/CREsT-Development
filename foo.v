module foo_circuit (
    IN_a, OUT_a );
  input  IN_a;
  output OUT_a;
  wire W1, W2;
  
  INV   G0(.A(IN_a), .Y(W1));
  INV   G1(.A(IN_a), .Y(W2));  
  AND2  G2(.A(W1), .B(W2), .Y(OUT_a));

endmodule