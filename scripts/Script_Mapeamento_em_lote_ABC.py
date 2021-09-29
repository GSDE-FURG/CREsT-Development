import glob
import os


#def readFolder():
#    pritn("EEEE")


def circuit_lib_type_switcher(arg):
    switcher = {"blif": blif_format,
                "bench": bench_format,
                "genlib": genlib_format}
    return switcher.get(arg, lambda: "Invalid arg")()


def blif_format():
    return "read_blif"


def bench_format():
    return "read_bench"


def genlib_format():
    return "read_genlib"


def get_circuit_name(filename):
    return filename.split(".")[0]


def get_lib_name(lib_name):
    return lib_name.split(".")[0]


def mapCircuit(nomeCircuit, genlib, bench_cmd, lib_cmd, output_path):
    nameCircuit = get_circuit_name(nomeCircuit)
    nameGenlib = get_lib_name(genlib)
    print("Circuito: " + nomeCircuit  + "   -> " + nameCircuit + " -> " + nameGenlib )
    print("     Genlib: " + genlib)

    # os.system("./abc -c 'read_blif " + str(nomeCircuit)+ "'" + " -c 'read_genlib " + genlib + "'" + " '-c map'" + "
    # '-c write_verilog " + nameCircuit + "_" + nameGenlib  + ".v'")

    first_step_cmd = "%s %s;" % (lib_cmd, str(genlib))
    second_step_cmd = "%s %s;" % (bench_cmd, str(nomeCircuit))
    third_step_cmd = "map;"
    fourth_step_cmd = "write_verilog %s/%s_%s.v" % (output_path, nameCircuit, nameGenlib)

    abc_full_cmd = "./abc -c \'%s %s %s %s\'" % (first_step_cmd, second_step_cmd, third_step_cmd, fourth_step_cmd)
    print(abc_full_cmd)
    os.system(abc_full_cmd)


# Script configuration file extensions
bench = "blif"
lib = "genlib"
#######################

#
circuitList = []
libList = []
bench_type = circuit_lib_type_switcher(bench)
lib_type = circuit_lib_type_switcher(lib)


for file in glob.glob("*.%s" % bench):
    circuitList.append(file)

for genlib in glob.glob("*.%s" % lib):
    libList.append(genlib)

for lib in libList:
    output_path = get_lib_name(lib)
    if not os.path.exists(output_path):
        os.makedirs(output_path)

    for i in circuitList:
        mapCircuit(i, lib, bench_type, lib_type, output_path)
    print("-----------")