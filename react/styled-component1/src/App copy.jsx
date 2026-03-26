import React from 'react'
import styled from 'styled-components'

const Button = styled.button`
  padding:10px 20px;
  border-radius: 6px;
  border:none;
  font-weight:600;
  cursor:pointer;
  transition:0.2s

  background: ${({variant}) => variant === 'outline' 
  ? "white" : variant === 'danger' 
  ? "#dc2626" : "#4f46e5"}
`;

function App() {
  return (
    <div style={{display:'flex', gap:'10px'}}>
      <Button>Primary</Button>
      <Button variant="outline">Outline</Button>
      <Button variant="danger">Danger</Button>
    </div>
  )
}

export default App