import React from 'react'

function ButtonExamples() {
  return (
    // space-y-8 세로로 작식 간격
    <div className='space-y-8'>
      <div>
        <h2 className="text-3xl font-bold mb-4 text-gray-800">버튼예제</h2>
        <p className="text-gray-600 mb-6">다양한 스타일의 버튼 컴포넌트들입니다.</p>
      </div>

      {/* 기본 버튼 */}
      <section>
        <h3 className="text-xl font-semibold mb-4 text-gray-700">1. 기본 버튼</h3>
        <div className="flex flex-wrap gap-4">
          <button className="px-6 py-2 bg-blue-500 text-white rounded hover:bg-blue-600 transition">
            Primary
          </button>
          <button className="px-6 py-2 bg-gray-500 text-white rounded hover:bg-gray-600 transition">
            Secondary
          </button>
          <button className="px-6 py-2 bg-green-500 text-white rounded hover:bg-green-600 transition">
            Success
          </button>
          <button className="px-6 py-2 bg-red-500 text-white rounded hover:bg-red-600 transition">
            Danger
          </button>
        </div>
      </section>

      {/* 아웃라인 버튼 */}
      <section>
        <h3 className="text-xl font-semibold mb-4 text-gray-700">2. 아웃라인 버튼</h3>
        <div className="flex flex-wrap gap-4">
          <button className="px-6 py-2 border-2 border-blue-500 text-blue-500 rounded hover:bg-blue-500 hover:text-white transition">
            Primary
          </button>
          <button className="px-6 py-2 border-2 border-gray-500 text-gray-500 rounded hover:bg-gray-500 hover:text-white transition">
            Secondary
          </button>
          <button className="px-6 py-2 border-2 border-green-500 text-green-500 rounded hover:bg-green-500 hover:text-white transition">
            Success
          </button>
        </div>
      </section>

      {/* 크기 버튼 */}
      <section>
        <h3 className="text-xl font-semibold mb-4 text-gray-700">3. 다양한 크기</h3>
        <div className="flex flex-wrap items-center gap-4">
          <button className="px-3 py-1 text-sm bg-blue-500 text-white rounded hover:bg-blue-600 transition">
            Small
          </button>
          <button className="px-6 py-2 bg-blue-500 text-white rounded hover:bg-blue-600 transition">
            Medium
          </button>
          <button className="px-8 py-3 text-lg bg-blue-500 text-white rounded hover:bg-blue-600 transition">
            Large
          </button>
        </div>
      </section>

       {/* 그라디언트 버튼 */}
      <section>
        <h3 className="text-xl font-semibold mb-4 text-gray-700">4. 그라디언트 버튼</h3>
        <div className="flex flex-wrap gap-4">
          <button className="px-6 py-2 bg-linear-to-r from-blue-500 to-purple-500 text-white rounded-lg shadow-md hover:shadow-lg transform hover:scale-105 transition">
            Gradient 1
          </button>
          <button className="px-6 py-2 bg-linear-to-r from-green-400 to-blue-500 text-white rounded-lg shadow-md hover:shadow-lg transform hover:scale-105 transition">
            Gradient 2
          </button>
          <button className="px-6 py-2 bg-linear-to-r from-pink-500 to-yellow-500 text-white rounded-lg shadow-md hover:shadow-lg transform hover:scale-105 transition">
            Gradient 3
          </button>
        </div>
      </section>

       {/* 아이콘 버튼 */}
      <section>
        <h3 className="text-xl font-semibold mb-4 text-gray-700">5. 아이콘 버튼</h3>
        <div className="flex flex-wrap gap-4">
          <button className="px-6 py-2 bg-blue-500 text-white rounded flex items-center gap-2 hover:bg-blue-600 transition">
            <span>⬇️</span>
            Download
          </button>
          <button className="px-6 py-2 bg-green-500 text-white rounded flex items-center gap-2 hover:bg-green-600 transition">
            <span>✓</span>
            Complete
          </button>
          <button className="px-6 py-2 bg-red-500 text-white rounded flex items-center gap-2 hover:bg-red-600 transition">
            <span>🗑️</span>
            Delete
          </button>
        </div>
      </section>
    </div>
  )
}

export default ButtonExamples