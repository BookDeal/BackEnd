import React, { useState } from 'react';
import axios from 'axios'; // axios를 가져옵니다.
import { useNavigate } from 'react-router-dom'; // React Router에서 useNavigate를 가져옵니다.

const LoginForm = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate(); // useNavigate 훅을 사용하여 페이지 이동 기능을 추가합니다.

    const handleSubmit = async (event) => {
        event.preventDefault();
        // 로그인 처리를 위한 로직을 여기에 작성합니다.
        console.log('Username:', username);
        console.log('Password:', password);

        try {
            // axios를 사용하여 로그인 요청을 보냅니다.
           await axios.post('http://localhost:8080/login', { // API 주소는 실제 서버 주소에 맞게 수정하세요.
                username,
                password,
            });


            // 로그인 성공 시 홈 화면으로 리다이렉션합니다.
            navigate('/'); // 홈 화면으로 이동
        } catch (error) {
            console.error('로그인 실패:', error);
            alert('로그인에 실패했습니다. 사용자 이름 또는 비밀번호를 확인하세요.');
        }
    };

    return (
        <div>
            <h2>로그인</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Username:</label>
                    <input
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Password:</label>
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                <button type="submit">로그인</button>
            </form>
        </div>
    );
};

export default LoginForm; // 기본 내보내기