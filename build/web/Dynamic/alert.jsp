<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Toast Notification</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f5f5f5;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      height: 100vh;
    }

    #toast-container {
      position: fixed;
      top: 0;
      left: 50%;
      transform: translateX(-50%);
      display: flex;
      flex-direction: column;
      align-items: center;
      gap: 10px;
      width: 100%;
      max-width: 400px;
      z-index: 9999;
      padding: 10px;
    }

    .toast {
      position: relative;
      display: flex;
      align-items: center;
      gap: 10px;
      padding: 10px 20px;
      border-radius: 5px;
      box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
      font-size: 14px;
      color: #333;
      background-color: #fff;
      opacity: 0;
      animation: slideIn 0.5s forwards, slideOut 0.5s 5.5s forwards;
      overflow: hidden;
      width: 100%;
    }

    .toast.success {
      border-left: 5px solid #4caf50;
    }

    .toast.error {
      border-left: 5px solid #f44336;
    }

    .toast.warning {
      border-left: 5px solid #ff9800;
    }

    .toast-icon {
      font-size: 16px;
      color: inherit;
    }

    .timeout-line {
      position: absolute;
      bottom: 0;
      left: 0;
      height: 4px;
      animation: progressBar 6s linear forwards;
    }

    .timeout-line.success {
      background-color: #4caf50;
    }

    .timeout-line.error {
      background-color: #f44336;
    }

    .timeout-line.warning {
      background-color: #ff9800;
    }

    @keyframes slideIn {
      from {
        opacity: 0;
        transform: translateY(-20px);
      }
      to {
        opacity: 1;
        transform: translateY(0);
      }
    }

    @keyframes slideOut {
      from {
        opacity: 1;
        transform: translateY(0);
      }
      to {
        opacity: 0;
        transform: translateY(-20px);
      }
    }

    @keyframes progressBar {
      from {
        width: 100%;
      }
      to {
        width: 0;
      }
    }
  </style>
</head>
<body>
  <div id="toast-container"></div>

  <button onclick="showToast('Success! Your operation was completed.', 'success')">Show Success</button>
  <button onclick="showToast('Error! Something went wrong.', 'error')">Show Error</button>
  <button onclick="showToast('Warning! Invalid input.', 'warning')">Show Warning</button>

  <script>
    function showToast(message, type) {
      const toastContainer = document.getElementById('toast-container');
      const toast = document.createElement('div');
      toast.className = `toast ${type}`;
      
      const icon = document.createElement('span');
      icon.className = 'toast-icon';
      icon.innerHTML = getIcon(type);

      const text = document.createElement('span');
      text.textContent = message;

      const timeoutLine = document.createElement('div');
      timeoutLine.className = `timeout-line ${type}`;

      toast.appendChild(icon);
      toast.appendChild(text);
      toast.appendChild(timeoutLine);
      toastContainer.appendChild(toast);

      setTimeout(() => {
        toast.addEventListener('animationend', () => toast.remove());
      }, 6000);
    }

    function getIcon(type) {
      switch (type) {
        case 'success':
          return '<i class="fas fa-check-circle" style="color: #4caf50;"></i>';
        case 'error':
          return '<i class="fas fa-times-circle" style="color: #f44336;"></i>';
        case 'warning':
          return '<i class="fas fa-exclamation-circle" style="color: #ff9800;"></i>';
        default:
          return '';
      }
    }
  </script>
</body>
</html>
