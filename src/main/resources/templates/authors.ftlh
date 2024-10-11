<#macro template>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <title>Author List</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="/webjars/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background-color: #f8f9fa;
            }
            .container {
                margin-top: 50px;
            }
            h1 {
                margin-bottom: 30px;
            }
            footer {
                margin-top: 50px;
                text-align: center;
                padding: 20px;
                background-color: #343a40;
                color: white;
            }
        </style>
    </head>
    <body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">News Management</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" href="/authors">Authors</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/news">News</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container">
        <h1>Authors</h1>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
            </tr>
            </thead>
            <tbody>
            <#list authors as author>
                <tr>
                    <td>${author.id}</td>
                    <td>${author.firstName}</td>
                    <td>${author.lastName}</td>
                    <td>${author.email}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>

    <footer>
        <p>&copy; 2024 IITU - Aruzhan Nurlan. All rights reserved.</p>
    </footer>

    <script src="/webjars/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
    </body>
    </html>
</#macro>