import logo from './logo.svg';
import React from 'react';
import SignupForm from './SignupForm'; // SignupForm 컴포넌트를 import 합니다.

const App = () => {
  return (
      <div className="App">
        <header>
          <h1>회원가입 페이지</h1>
        </header>
        <main>
          <SignupForm />
        </main>
      </div>
  );
}

export default App;
