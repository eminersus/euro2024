// src/services/api.js

import axios from 'axios';

const API_URL = 'http://localhost:8080/api';

export const registerUser = (user) => {
    return axios.post(`${API_URL}/registration`, user);
};

export const loginUser = (user) => {
    return axios.post(`${API_URL}/login`, user);
};

export const confirmRegistration = (token) => {
    return axios.get(`${API_URL}/confirm`, { params: { token } });
};
