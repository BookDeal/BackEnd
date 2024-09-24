// MyPage.js
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom'; // React Router에서 useNavigate를 가져옵니다.
const MyPage = () => {
    const [username, setUsername] = useState('');

    useEffect(() => {
        const fetchUserInfo = async () => {
            try {
                const response = await axios.get('http://localhost:8080/info', {
                    withCredentials: true, // 쿠키와 함께 요청
                });
                setUsername(response.data); // 응답에서 username을 설정
            } catch (error) {
                console.error('사용자 정보를 가져오는 데 실패했습니다:', error);
            }
        };

        fetchUserInfo();
    }, []);

    return (
        <div>
            <h2>마이페이지</h2>
            {username ? (
                <p>안녕하세요, {username}님!</p>
            ) : (
                <p>사용자 정보를 불러오는 중...</p>
            )}
        </div>
    );
};

export default MyPage;
