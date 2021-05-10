<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="author" content="Rocky">
  <meta name="viewport" content="width=device-width,initial-scale=1">
  <title>My Deposit Page &mdash; Bootstrap 4 Login Page Snippet</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <link rel="stylesheet" type="text/css" href="resources/css/login.css">
</head>

<body class="my-login-page">
<section class="h-100">
  <div class="container h-100">
    <div class="row justify-content-md-center h-100">
      <div class="card-wrapper">
        <div class="brand">
          <img src="resources/img/logo.jpg" alt="bootstrap 4 login page">
        </div>
        <div class="title">
          <h2 class="card-title text-center">CHEESE BANK</h2>
        </div>
        <div class="card fat">
          <div class="card-body">
            <h3 class="card-title ">Welcome ${user}</h3>

            <form action="save" method="POST" class="my-login-validation" novalidate="">
              <h6> Deposit Money</h6>
              <div class="form-group">
                <label for="save"></label>
                <input id="save" type="text" class="form-control" name="save" value="" required autofocus>
                <div class="invalid-feedback d-block">
                  ${deposit_error}
                </div>
                <div class="form-text text-muted">
                  put the amount of money you want to deposit $
                </div>
              </div>

              <div class="form-group m-0">
                <button type="submit" class="btn btn-primary btn-block">
                  Send
                </button>
              </div>
            </form>
            <hr/>
            <form action="withdraw" method="POST" class="my-login-validation" novalidate="">
              <h6> Withdraw Money</h6>
              <div class="form-group">
                <label for="withdraw"></label>
                <input id="withdraw" type="text" class="form-control" name="withdraw" value="" required autofocus>
                <div class="invalid-feedback d-block">
                  ${withdraw_error}
                </div>
                <div class="form-text text-muted">
                  put the amount of money you want to deposit $
                </div>
              </div>

              <div class="form-group m-0">
                <button type="submit" class="btn btn-primary btn-block">
                  Withdraw
                </button>
              </div>
            </form>
            <hr/>
            <h4> ${user} 's Balance: ${balance} </h4>
            <hr/>
            <div class="form-group m-0">

              <form action="logout" method="get">
                <button class="btn btn-danger btn-block" >
                  Logout
                </button>
              </form>
            </div>
          </div>
        </div>
        <div class="footer">
          Copyright &copy; 2017 &mdash; Your Company
        </div>
      </div>
    </div>
  </div>
</section>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
</body>
</html>