import React, { useState } from 'react';
import axios from 'axios';
import './SignupForm.css'; // CSS 파일을 import 합니다.

const SignupForm = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const [successMessage, setSuccessMessage] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();

        const userDTO = {
            username,
            password,
        };

        try {
            const response = await axios.post('/signup',userDTO);
            if (response.status === 200) {
                setSuccessMessage('회원가입 성공!');
                setError('');
            }
        } catch (err) {
            setError('회원가입에 실패했습니다.');
            setSuccessMessage('');
        }
    };

    return (
        <div className="signup-form">
            <h2>회원가입</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>사용자 이름:</label>
                    <input
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>비밀번호:</label>
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                <button type="submit">회원가입</button>
            </form>
            {error && <p className="error-message">{error}</p>}
            {successMessage && <p className="success-message">{successMessage}</p>}
        </div>
    );
};

export default SignupForm;
