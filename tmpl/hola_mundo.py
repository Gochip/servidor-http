a = ""
for i in range(1000):
    a += "PYTHON 3<br />"

cadena = """
<!DOCTYPE html>
<html>
    <head>
        <title>HOLA MUNDO</title>
    </head>
    <body>
        %s
    </body>
</html>
""" % (a)
print (cadena)
