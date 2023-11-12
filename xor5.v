// Benchmark "../../Simulations/03_PTM_SPR_PGM/_run_simulations/bench/xor5" written by ABC on Thu Nov  9 16:58:52 2023

module \../../Simulations/03_PTM_SPR_PGM/_run_simulations/bench/xor5  ( 
    d, c, b, a, e,
    xor5  );
  input  d, c, b, a, e;
  output xor5;
  wire new_n7_, new_n8_, new_n9_;
  XOR2xp5_ASAP7_75t_R       g0(.A(a), .B(e), .Y(new_n7_));
  XNOR2xp5_ASAP7_75t_R      g1(.A(d), .B(new_n7_), .Y(new_n8_));
  XNOR2xp5_ASAP7_75t_R      g2(.A(b), .B(c), .Y(new_n9_));
  XOR2xp5_ASAP7_75t_R       g3(.A(new_n9_), .B(new_n8_), .Y(xor5));
endmodule


