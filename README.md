## Профильное задание

## Содержание

- [О проекте](#title1)

- [Screencast](#title2)

- [Структура проекта](#title3)

- [Основной стек технологий](#title4)

## <a id="title1"> О проекте </a>

Android-приложение для просмотра видео.

- На главном экране отображается список видео.

- При нажатии на видео из списка открывается экран просмотра.

- Реализована возможность "потянуть вниз" для обновления списка.

- Реализован поиск по названию видео.

- Список видео подгружается из удаленного API - https://publit.io/

- Реализовано кеширование списка видео с помощью Room.

- Добавлен обработчик ошибок при ошибке загрузке данных с API.

- Приложение корректно работает в портретном и ландшафтном режиме

## <a id="title2"> ScreenCast </a>

<table>
  <tr>
    <td>
      <video src="https://github.com/user-attachments/assets/270897f8-c464-4993-8e6b-0a46c8f80e7e" controls width="100%"></video>
    </td>
    <td>
      <video src="https://github.com/user-attachments/assets/89f2e0c0-9157-4939-ad8a-79964248eb0c" controls width="100%"></video>
    </td>
    <td>
      <video src="https://github.com/user-attachments/assets/85b0b172-674e-4f99-ad22-8d4523dc2e0f" controls width="100%"></video>
    </td>
  </tr>
</table>

## <a id="title3"> Структура проекта </a>

- MVVM

## <a id="title4"> Основной стек технологий </a>

- Coil
- Hilt / Dagger
- JetpackCompose
- Navigator
- Retrofit
- Room
