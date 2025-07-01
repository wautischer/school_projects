<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Comment for User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body>
<div class="container">
    <h1 class="mt-5">Add Comment for User</h1>
    <form action="addComment" method="get" class="mt-3">
        <div class="mb-3">
            <label for="username" class="form-label">Name of User:</label>
            <input type="text" id="username" name="uname" class="form-control" value="">
        </div>
        <div class="mb-3">
            <label for="comment" class="form-label">Comment:</label>
            <input type="text" id="comment" name="cname" class="form-control" value="">
        </div>
        <button type="submit" class="btn btn-primary">Insert Comment</button>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
