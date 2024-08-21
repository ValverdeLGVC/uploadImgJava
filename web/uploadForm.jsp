<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Aluno</title>
    <style>
        #preview {
            margin-top: 10px;
            max-width: 300px;
            max-height: 300px;
        }
    </style>
    <script>
        function previewImage(event) {
            var reader = new FileReader();
            reader.onload = function(){
                var output = document.getElementById('preview');
                output.src = reader.result;
            };
            reader.readAsDataURL(event.target.files[0]);
        }
    </script>
</head>
<body>
    <h2>Cadastro de Aluno</h2>
    <form action="upload" method="post" enctype="multipart/form-data">
        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" required><br><br>

        <label for="foto">Foto:</label>
        <input type="file" id="foto" name="foto" accept="image/*" required onchange="previewImage(event)"><br><br>

        <img id="preview" alt="Prévia da imagem selecionada"><br><br>

        <input type="submit" value="Salvar">
    </form>
</body>
</html>
