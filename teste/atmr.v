//Benchmark atmr_alu14

module atmr_alu14(i0, z0);
 input i0;
 output z0;
 wire  zori10, zori20, zori30;
  INV        o00(.A(i0), .Y(zori10));
  INV        m00(.A(i0), .Y(zori20));
  INV        u00(.A(i0), .Y(zori30));
  VOTADOR g0(.A(zori10), .B(zori20), .C(zori30), .Y(z0));
endmodule