import React from 'react'

function Wrapper({children}) {
  const style = {
    border: "3px solid #282c34",
    padding:"20px",
    borderRadius: "10px",
    margin: "10px",
    backgroundColor:"#f9f9f9"
  }
  return (
    <div style={style}>{children}</div>
  )
}

export default Wrapper