// Handle Registration
const registerUser = async (e) => {
    e.preventDefault();

    const username = document.getElementById('registerUsername').value;
    const password = document.getElementById('registerPassword').value;
    const role = document.getElementById('registerRole').value;

    const result = await postData('http://localhost:8080/auth/register', { username, password, role });
    const registerMessage = document.getElementById('registerMessage');

    if (result.token) {
        registerMessage.textContent = `Success! You can now log in.`;
    } else if (result.error) {
        registerMessage.textContent = `Error: ${result.error}`;
    }
};

// Handle Login
const loginUser = async (e) => {
    e.preventDefault();

    const username = document.getElementById('loginUsername').value;
    const password = document.getElementById('loginPassword').value;

    const result = await postData('http://localhost:8080/auth/login', { username, password });
    const loginMessage = document.getElementById('loginMessage');

    if (result.token) {
        loginMessage.textContent = `Login successful! Token: ${result.token}`;
        localStorage.setItem('authToken', result.token);  // Store the token for future requests
    } else if (result.error) {
        loginMessage.textContent = `Error: ${result.error}`;
    }
};

// Helper function for POST requests
async function postData(url = '', data = {}) {
    const response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    });

    return await response.json();
}

// Event listeners for form submissions
document.getElementById('registerForm').addEventListener('submit', registerUser);
document.getElementById('loginForm').addEventListener('submit', loginUser);
