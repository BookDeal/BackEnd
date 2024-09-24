
import React from 'react';
import SignupForm from './SignupForm'; // SignupForm 컴포넌트를 import 합니다.
import LoginForm from './LoginForm';   // 로그인 폼 컴포넌트
import HomePage from './Home';     // 루트 페이지 컴포넌트
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom'; // react-router-dom에서 필요한 컴포넌트 임포트
import MyPage from "./MyPage";
import './App.css';  // CSS 파일을 임포트합니다.
const App = () => {
    return (
        <Router>
            <div className="App">
                <header>
                    <h1>Book_Deal</h1>
                    <nav>
                        <ul>

                            <li><Link to="/signup">회원가입</Link></li>
                            <li><Link to="/login">로그인</Link></li>
                            <li><Link to="/mypage">마이페이지</Link></li> {/* 마이페이지 링크 추가 */}
                        </ul>
                    </nav>
                </header>
                <main>
                    <Routes>
                        <Route path="/" element={<HomePage />} /> {/* 루트 페이지 */}
                        <Route path="/signup" element={<SignupForm />} />
                        <Route path="/login" element={<LoginForm />} />
                        <Route path="/mypage" element={<MyPage />} /> {/* 마이페이지 라우트 추가 */}
                    </Routes>
                </main>
            </div>
        </Router>
    );
}

export default App;
