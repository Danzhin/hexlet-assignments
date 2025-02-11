@import gg.jte.Content

@param Content content

<!DOCTYPE html>

<html lang="ru">

  <head>
    <meta charset="UTF-8">
    <title>Hexlet Javalin</title>
  </head>

  <body>

    <header>
      <nav>
        <ul>
          <li><a href="/">Главная</a></li>
          <li><a href="/users">Пользователи</a></li>
        </ul>
      </nav>
    </header>

    <main>
        ${content}
    </main>

    <footer>
        <p>Наш email: hexlet@gmail.com</p>
    </footer>

  </body>

</html>