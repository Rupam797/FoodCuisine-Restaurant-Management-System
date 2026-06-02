/**
 * Toast Notification Utility
 * Dynamically spawns animated toast elements at the top right of the viewport.
 * 
 * @param {string} message - The message text to display.
 * @param {string} type - Notification level: 'success', 'error', 'info', or 'warning'.
 */
function showToast(message, type = 'info') {
    // 1. Locate or create toast container
    let container = document.querySelector('.toast-container');
    if (!container) {
        container = document.createElement('div');
        container.className = 'toast-container';
        document.body.appendChild(container);
    }

    // 2. Create toast element
    const toast = document.createElement('div');
    toast.className = `toast ${type}`;

    // 3. Select appropriate FontAwesome icon based on type
    let iconClass = 'fa-circle-info';
    if (type === 'success') {
        iconClass = 'fa-circle-check';
    } else if (type === 'error') {
        iconClass = 'fa-circle-xmark';
    } else if (type === 'warning') {
        iconClass = 'fa-triangle-exclamation';
    }

    // 4. Fill in toast markup
    toast.innerHTML = `
        <i class="fa-solid ${iconClass} toast-icon"></i>
        <div class="toast-message">${message}</div>
        <i class="fa-solid fa-xmark toast-close" onclick="closeToast(this.parentElement)"></i>
    `;

    // 5. Append to container
    container.appendChild(toast);

    // 6. Trigger CSS transition (next frame)
    setTimeout(() => {
        toast.classList.add('show');
    }, 10);

    // 7. Auto-dismiss after 4.5 seconds
    setTimeout(() => {
        closeToast(toast);
    }, 4500);
}

/**
 * Dismisses a toast element with a slide-out transition before removing it from DOM.
 * @param {HTMLElement} toast - The toast element to close.
 */
function closeToast(toast) {
    if (!toast) return;
    toast.classList.remove('show');
    // Remove from DOM after transition completes (400ms match transition time)
    setTimeout(() => {
        toast.remove();
        
        // Remove empty container to clean up DOM
        const container = document.querySelector('.toast-container');
        if (container && container.children.length === 0) {
            container.remove();
        }
    }, 400);
}
